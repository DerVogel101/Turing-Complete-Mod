package name.turingcomplete.init;

import name.turingcomplete.TuringComplete;
import name.turingcomplete.blocks.truthtable.TruthTableBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public class blockEntityInit {
    public static BlockEntityType<TruthTableBlockEntity> TRUTH_TABLE_BLOCK_ENTITY = register("truth_table", blockInit.TRUTH_TABLE, TruthTableBlockEntity::new);

    public static <T extends BlockEntity> BlockEntityType<T> register(String id, Block block, FabricBlockEntityTypeBuilder.Factory<T> factory) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, TuringComplete.id(id), FabricBlockEntityTypeBuilder.create(factory, block).build());
    }

    public static void load(){}
}