package name.turingcomplete.blocks.truthtable;

import net.minecraft.recipe.RecipeType;

public class TruthTableRecipeType implements RecipeType<TruthTableRecipe> {
    public static final TruthTableRecipeType INSTANCE = new TruthTableRecipeType();
    public static final String ID = "truth_table";

    private TruthTableRecipeType() {}
}
