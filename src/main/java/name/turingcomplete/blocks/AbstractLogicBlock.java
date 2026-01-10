package name.turingcomplete.blocks;

import org.jetbrains.annotations.MustBeInvokedByOverriders;

import com.mojang.serialization.MapCodec;

import name.turingcomplete.init.propertyInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComparatorBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SideShapeType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

// anything on a logic plate
public abstract class AbstractLogicBlock
        extends HorizontalFacingBlock
        implements ConnectsToRedstone {

    public static final BooleanProperty MIRRORED = propertyInit.SWAPPED_DIR;

    protected AbstractLogicBlock(Settings settings) {
        super(settings);
        if (canMirror()) {
            setDefaultState(getDefaultState().with(MIRRORED, false));
        }
    }

    // allow immediate block state changes in response to input
    protected void onInputChange(World world, BlockPos gatePos, BlockState gateState) {}

    protected int getInputStrength(World world, BlockPos pos, BlockState state, RelativeSide inputSide) {
        Direction checkDirection = inputSide.withBackDirection(state.get(FACING));
        BlockPos checkPos = pos.offset(checkDirection);
        return world.getEmittedRedstonePower(checkPos, checkDirection);
    }

    protected boolean getInputActive(World world, BlockPos pos, BlockState state, RelativeSide inputSide) {
        return getInputStrength(world, pos, state, inputSide) > 0;
    }

    // warning: called in constructor
    protected boolean canMirror() {
        return false;
    }

    // warning: called in constructor
    protected boolean isDirectional() {
        return true;
    }

    protected boolean canMirrorHere(BlockPos mainPos, BlockState mainState) {
        return false;
    }

    // default output update (directional)
    protected void updateOutputBlock(World world, BlockPos sourcePos, Direction direction) {
        BlockPos targetPos = sourcePos.offset(direction);
        world.updateNeighbor(targetPos, this,  null);
        world.updateNeighborsExcept(targetPos, this, direction.getOpposite(), null);
    }

    protected void updateOutputBlock(World world, BlockPos sourcePos, BlockPos targetPos) {
        Direction toSourceDirection = null;

        int dx = targetPos.getX() - sourcePos.getX();
        int dy = targetPos.getY() - sourcePos.getY();
        int dz = targetPos.getZ() - sourcePos.getZ();

        if (dx == 1 && dy == 0 && dz == 0) toSourceDirection = Direction.WEST;
        else if (dx == -1 && dy == 0 && dz == 0) toSourceDirection = Direction.EAST;
        else if (dx == 0 && dy == 1 && dz == 0) toSourceDirection = Direction.DOWN;
        else if (dx == 0 && dy == -1 && dz == 0) toSourceDirection = Direction.UP;
        else if (dx == 0 && dy == 0 && dz == 1) toSourceDirection = Direction.NORTH;
        else if (dx == 0 && dy == 0 && dz == -1) toSourceDirection = Direction.SOUTH;

        world.updateNeighbor(targetPos, this, null);

        if (toSourceDirection == null) {
            world.updateNeighborsAlways(targetPos, this, null);
        } else {
            world.updateNeighborsExcept(targetPos, this, toSourceDirection, null);
        }
    }

    protected boolean canPlaceAbove(WorldView world, BlockPos pos, BlockState state) {
        return state.isSideSolid(world, pos, Direction.UP, SideShapeType.RIGID);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return this.canPlaceAbove(world, pos.down(), world.getBlockState(pos.down()));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return AbstractBlock.createCodec(ComparatorBlock::new);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = getDefaultState();
        if (isDirectional()) {
            state = state.with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
        }
        return state;
    }

    @Override
    protected int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return getWeakRedstonePower(state, world, pos, direction);
    }

    // Fabric 1.21.1 FIX: new Redstone-Dust-API
    public boolean dustConnectsToThis(
            BlockState state,
            BlockView world,
            BlockPos pos,
            WireOrientation orientation
    ) {
        return false;
    }

    @MustBeInvokedByOverriders
    protected void neighborUpdate(
            BlockState state,
            World world,
            BlockPos pos,
            Block sourceBlock,
            BlockPos sourcePos,
            boolean notify
    ) {
        if (!canPlaceAt(state, world, pos)) {
            world.breakBlock(pos, true);
            for (Direction direction : DIRECTIONS) {
                world.updateNeighborsAlways(pos.offset(direction), this, null);
            }
        }
    }

    @Override
    @MustBeInvokedByOverriders
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        if (isDirectional()) {
            builder.add(FACING);
        }
        if (canMirror()) {
            builder.add(MIRRORED);
        }
    }

    public enum RelativeSide {
        FRONT,
        BACK,
        LEFT,
        RIGHT;

        public Direction withFrontDirection(Direction direction) {
            if (direction.getAxis() == Axis.Y) {
                throw new IllegalArgumentException("direction must not be vertical");
            }
            return switch (this) {
                case FRONT -> direction;
                case BACK -> direction.getOpposite();
                case LEFT -> direction.getAxis() == Axis.Z
                        ? Direction.from(Axis.X, direction.getDirection())
                        : Direction.from(Axis.Z, direction.getDirection().getOpposite());
                case RIGHT -> direction.getAxis() == Axis.Z
                        ? Direction.from(Axis.X, direction.getDirection().getOpposite())
                        : Direction.from(Axis.Z, direction.getDirection());
            };
        }

        public Direction withBackDirection(Direction direction) {
            return this.getOpposite().withFrontDirection(direction);
        }

        public RelativeSide getOpposite() {
            return switch (this) {
                case BACK -> FRONT;
                case FRONT -> BACK;
                case LEFT -> RIGHT;
                case RIGHT -> LEFT;
            };
        }
    }
}
