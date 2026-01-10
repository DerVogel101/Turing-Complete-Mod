package name.turingcomplete;

import name.turingcomplete.data.provider.TuringCompleteLootTableProvider;
import name.turingcomplete.data.provider.TuringCompleteModelProvider;
import name.turingcomplete.data.provider.TuringCompleteRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

public class TuringCompleteDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(TuringCompleteRecipeProvider::new);
		pack.addProvider(TuringCompleteLootTableProvider::new);

		pack.addProvider(TuringCompleteModelProvider::new);
	}

	@Override
	public void buildRegistry (RegistryBuilder registryBuilder){

	}
}
