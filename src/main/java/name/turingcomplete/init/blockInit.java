package name.turingcomplete.init;

import name.turingcomplete.TuringComplete;
import name.turingcomplete.blocks.truthtable.TruthTable;
import name.turingcomplete.blocks.block.*;
import name.turingcomplete.blocks.multiblock.Adder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

public class blockInit {
    public static final NANDGateBlock NAND_GATE = registerWithItem(
            "nand_gate_block",
            NANDGateBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));


    public static final NOTGateBlock NOT_GATE = registerWithItem("not_gate_block",
            NOTGateBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final ANDGateBlock AND_GATE = registerWithItem("and_gate_block",
            ANDGateBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final LogicBasePlateBlock LOGIC_BASE_PLATE_BLOCK = registerWithItem("logic_base_plate_block",
            LogicBasePlateBlock::new,
            Block.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final ORGateBlock OR_GATE = registerWithItem("or_gate_block",
            ORGateBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final NORGateBlock NOR_GATE = registerWithItem("nor_gate_block",
            NORGateBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final XNORGateBlock XNOR_GATE = registerWithItem("xnor_gate_block",
            XNORGateBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final XORGateBlock XOR_GATE = registerWithItem("xor_gate_block",
            XORGateBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    //===============================================================================================

    public static final ThreeANDGateBlock THREE_AND_GATE = registerWithItem("3and_gate_block",
            ThreeANDGateBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final ThreeORGateBlock THREE_OR_GATE = registerWithItem("3or_gate_block",
            ThreeORGateBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    //===============================================================================================

    public static final SwitchGateBlock SWITCH_GATE = registerWithItem("switch_gate_block",
            SwitchGateBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final MemoryCellBlock MEMORY_CELL = registerWithItem("memory_cell_gate",
            MemoryCellBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    //===============================================================================================

    public static final Adder HALF_ADDER = registerWithItem("half_adder",
            settings -> new Adder(settings, false),
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final Adder FULL_ADDER = registerWithItem("full_adder",
            settings -> new Adder(settings, true),
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final BiDirectionalRedstoneBridgeBlock BI_DIRECTIONAL_REDSTONE_BRIDGE_BLOCK = registerWithItem("bi_directional_redstone_bridge_block",
            BiDirectionalRedstoneBridgeBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    //===============================================================================================

    public static final OmniDirectionalRedstoneBridgeBlock OMNI_DIRECTIONAL_REDSTONE_BRIDGE_BLOCK = registerWithItem("omni_directional_redstone_bridge_block",
            OmniDirectionalRedstoneBridgeBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final SRLatchBlock SR_LATCH_BLOCK = registerWithItem("sr_latch_block",
            SRLatchBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final JKLatchBlock JK_LATCH_BLOCK = registerWithItem("jk_latch_block",
            JKLatchBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));

    public static final TLatchBlock T_LATCH_BLOCK = registerWithItem("t_latch_block",
            TLatchBlock::new,
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));


    public static final TruthTable TRUTH_TABLE = registerWithItem("truth_table_block",
            TruthTable::new,
            Block.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.DESTROY));


    private static <T extends Block> T registerWithItem(
            String name,
            java.util.function.Function<AbstractBlock.Settings, T> factory,
            AbstractBlock.Settings settings
    ) {
        var id = TuringComplete.id(name);

        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, id);
        settings = settings.registryKey(blockKey);
        T block = factory.apply(settings);
        Registry.register(Registries.BLOCK, blockKey, block);

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings itemSettings = new Item.Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(itemKey);

        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, itemSettings));
        return block;
    }
    public static void load(){}
}
