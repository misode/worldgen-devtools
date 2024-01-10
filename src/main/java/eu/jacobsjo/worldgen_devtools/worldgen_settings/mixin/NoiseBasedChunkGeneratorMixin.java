package eu.jacobsjo.worldgen_devtools.worldgen_settings.mixin;


import eu.jacobsjo.worldgen_devtools.worldgen_settings.WorldgenSettingsInit;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseBasedChunkGeneratorMixin {

    @Inject(method="buildSurface(Lnet/minecraft/server/level/WorldGenRegion;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/chunk/ChunkAccess;)V", at=@At("HEAD"), cancellable = true)
    public void buildSurface(WorldGenRegion level, StructureManager structureManager, RandomState random, ChunkAccess chunk, CallbackInfo ci){
        if (!level.getLevelData().getGameRules().getRule(WorldgenSettingsInit.MAX_CHUNK_STATUS).get().surface){
            ci.cancel();
        };
    }

    @Inject(method= "applyCarvers", at=@At("HEAD"), cancellable = true)
    public void applyCarvers(WorldGenRegion level, long seed, RandomState random, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunk, GenerationStep.Carving step, CallbackInfo ci) {
        if (!level.getLevelData().getGameRules().getRule(WorldgenSettingsInit.MAX_CHUNK_STATUS).get().carvers){
            ci.cancel();
        };
    }

}
