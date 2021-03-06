package gatocreador887.greenvoidislands.common.world.biome;

import java.util.Random;

import gatocreador887.greenvoidislands.common.block.BlockDoubleTallIslandGrass;
import gatocreador887.greenvoidislands.common.block.BlockTallIslandGrass;
import gatocreador887.greenvoidislands.common.core.GVIBlockManager;
import gatocreador887.greenvoidislands.common.world.gen.feature.WorldGenTallIslandGrass;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomePlainsIsland extends BiomeGVI {
	
	public BiomePlainsIsland(BiomeProperties properties) {
		super(properties);
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.topBlock = GVIBlockManager.ISLAND_GRASS.getDefaultState();
		this.decorator.treesPerChunk = 0;
		this.decorator.extraTreeChance = 0.05F;
		this.decorator.flowersPerChunk = 4;
		this.decorator.grassPerChunk = 10;
		this.flowers.clear();
		
	}
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		
		double d0 = GRASS_COLOR_NOISE.getValue((double) (pos.getX() + 8) / 200.0D, (double) (pos.getZ() + 8) / 200.0D);
		
		if (d0 < -0.8D) {
			
			this.decorator.flowersPerChunk = 15;
			this.decorator.grassPerChunk = 5;
			
		} else {
			
			this.decorator.flowersPerChunk = 4;
			this.decorator.grassPerChunk = 10;
			
			DOUBLE_TALL_ISLAND_GRASS_GENERATOR.setPlantType(BlockDoubleTallIslandGrass.EnumPlantType.GRASS);
			
			if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS)) {
				
				for (int i = 0; i < 7; ++i) {
					
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					
					DOUBLE_TALL_ISLAND_GRASS_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
					
				}
				
			}
			
		}
		
		super.decorate(worldIn, rand, pos);
		
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		
		return (WorldGenAbstractTree) (rand.nextInt(3) == 0 ? SHRONK_TREE_FEATURE : TREE_FEATURE);
		
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		
		return new WorldGenTallIslandGrass(BlockTallIslandGrass.EnumType.GRASS);
		
	}
	
}
