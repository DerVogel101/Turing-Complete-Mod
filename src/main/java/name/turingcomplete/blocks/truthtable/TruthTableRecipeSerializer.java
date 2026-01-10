package name.turingcomplete.blocks.truthtable;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class TruthTableRecipeSerializer implements RecipeSerializer<TruthTableRecipe> {
    public static final TruthTableRecipeSerializer INSTANCE = new TruthTableRecipeSerializer();
    public static final Identifier ID = Identifier.of("turingcomplete","truth_table");

    // MapCodec for JSON serialization using RecordCodecBuilder
    public static final MapCodec<TruthTableRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Identifier.CODEC.fieldOf("id").forGetter(TruthTableRecipe::getId),
        ItemStack.CODEC.fieldOf("output").forGetter(TruthTableRecipe::getOutput),
        ItemStack.CODEC.fieldOf("input1").forGetter(TruthTableRecipe::getInput1),
        ItemStack.CODEC.fieldOf("input2").forGetter(TruthTableRecipe::getInput2),
        ItemStack.CODEC.fieldOf("input3").forGetter(TruthTableRecipe::getInput3),
        ItemStack.CODEC.fieldOf("input4").forGetter(TruthTableRecipe::getInput4)
    ).apply(instance, TruthTableRecipe::new));

    // PacketCodec for network packet serialization
    public static final PacketCodec<RegistryByteBuf, TruthTableRecipe> PACKET_CODEC = PacketCodec.tuple(
        Identifier.PACKET_CODEC, TruthTableRecipe::getId,
        ItemStack.PACKET_CODEC, TruthTableRecipe::getOutput,
        ItemStack.PACKET_CODEC, TruthTableRecipe::getInput1,
        ItemStack.PACKET_CODEC, TruthTableRecipe::getInput2,
        ItemStack.PACKET_CODEC, TruthTableRecipe::getInput3,
        ItemStack.PACKET_CODEC, TruthTableRecipe::getInput4,
        TruthTableRecipe::new
    );

    @Override
    public MapCodec<TruthTableRecipe> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, TruthTableRecipe> packetCodec() {
        return PACKET_CODEC;
    }
}
