package name.turingcomplete.blocks.truthtable;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.IngredientPlacement;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class TruthTableRecipe implements Recipe<TruthTableRecipeInput> {
    private final Identifier id;
    private final ItemStack output;
    private final ItemStack input1;
    private final ItemStack input2;
    private final ItemStack input3;
    private final ItemStack input4;

    public TruthTableRecipe(Identifier id, ItemStack output, ItemStack in1, ItemStack in2, ItemStack in3, ItemStack in4){
        this.id = id;
        this.output = output;
        this.input1 = in1;
        this.input2 = in2;
        this.input3 = in3;
        this.input4 = in4;
    }

    public ItemStack getInput1() { return input1; }
    public ItemStack getInput2() { return input2; }
    public ItemStack getInput3() { return input3; }
    public ItemStack getInput4() { return input4; }
    public Identifier getId() { return id; }
    public ItemStack getOutput() { return output; }

    @Override
    public boolean matches(TruthTableRecipeInput input, World world) {
        // Implement your matching logic here
        return false;
    }

    @Override
    public ItemStack craft(TruthTableRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<TruthTableRecipeInput>> getSerializer() {
        return TruthTableRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<? extends Recipe<TruthTableRecipeInput>> getType() {
        return TruthTableRecipeType.INSTANCE;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.NONE;
    }
}
