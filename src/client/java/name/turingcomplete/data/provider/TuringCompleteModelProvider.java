package name.turingcomplete.data.provider;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import name.turingcomplete.init.blockInit;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Set;

public class TuringCompleteModelProvider extends FabricModelProvider {
    public TuringCompleteModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator gen) {
        for (Block block : ITEM_USES_BLOCK_MODEL) {
            // block model already exists at assets/<modid>/models/block/<id>.json
            Identifier existingBlockModelId = ModelIds.getBlockModelId(block);

            // generate ONLY the item model definition that points to that model
            gen.registerParentedItemModel(block, existingBlockModelId);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator gen) {
        for (var id : Registries.BLOCK.getIds()) {
            if (!id.getNamespace().equals("turingcomplete")) continue;

            Block block = Registries.BLOCK.get(id);
            if (ITEM_USES_BLOCK_MODEL.contains(block)) continue;

            Item item = Item.fromBlock(block);

            // Skip blocks that intentionally have no item form
            if (item == Items.AIR) continue;

            gen.register(item, Models.GENERATED);
        }
    }

    private static final Set<Block> ITEM_USES_BLOCK_MODEL = Set.of(
            blockInit.TRUTH_TABLE,
            blockInit.LOGIC_BASE_PLATE_BLOCK);
}
