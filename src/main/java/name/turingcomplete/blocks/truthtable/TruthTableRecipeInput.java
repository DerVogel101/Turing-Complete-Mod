package name.turingcomplete.blocks.truthtable;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record TruthTableRecipeInput(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4) implements RecipeInput {
    
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> input1;
            case 1 -> input2;
            case 2 -> input3;
            case 3 -> input4;
            default -> throw new IllegalArgumentException("No item for slot " + slot);
        };
    }
    
    @Override
    public int size() {
        return 4;
    }
}
