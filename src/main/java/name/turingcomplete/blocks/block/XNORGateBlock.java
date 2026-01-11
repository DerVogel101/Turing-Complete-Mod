package name.turingcomplete.blocks.block;

import com.mojang.serialization.MapCodec;
import name.turingcomplete.blocks.AbstractSimpleGate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class XNORGateBlock extends AbstractSimpleGate {
    public static final MapCodec<XNORGateBlock> CODEC = Block.createCodec(XNORGateBlock::new);
    public XNORGateBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean evaluateGate(World world, BlockPos gatePos, BlockState gateState) {
        boolean left = getInputActive(world, gatePos, gateState,RelativeSide.LEFT);
        boolean right = getInputActive(world, gatePos, gateState,RelativeSide.RIGHT);
        return ! (left ^ right);
    }
}