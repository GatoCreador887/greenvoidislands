package creepersgalore.greenvoidislands.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import creepersgalore.greenvoidislands.client.core.GVICreativeTabManager;
import creepersgalore.greenvoidislands.common.block.BlockGVIPortal;
import creepersgalore.greenvoidislands.common.core.GVIBlockManager;

public class ItemFoligenuAndObsidian extends Item
{
    public ItemFoligenuAndObsidian()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(5);
        this.setCreativeTab(GVICreativeTabManager.gear);
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	
    	ItemStack par1ItemStack = player.getHeldItem(hand);

		int par4 = pos.getX();
		int par5 = pos.getY();
		int par6 = pos.getZ();

		int par7 = facing.getIndex();

		if (par7 == 0) {
			par5--;
		}
		if (par7 == 1) {
			par5++;
		}
		if (par7 == 2) {
			par6--;
		}
		if (par7 == 3) {
			par6++;
		}
		if (par7 == 4) {
			par4--;
		}
		if (par7 == 5) {
			par4++;
		}
		if (!player.canPlayerEdit(new BlockPos(par4, par5, par6), facing, par1ItemStack)) {
			return EnumActionResult.FAIL;
		} else
        {
			pos = pos.offset(facing);
			
            if (worldIn.isAirBlock(pos))
            {
                worldIn.playSound(player, new BlockPos(par4, par5, par6), SoundEvents.ENTITY_ZOMBIE_INFECT, SoundCategory.BLOCKS, 1.0F,itemRand.nextFloat() * 0.4F + 1.3F);
				
    			//((BlockGVIPortal) GVIBlockManager.gvi_portal).tryToCreatePortal(worldIn, par4, par5, par6);
    			
    			if (!((BlockGVIPortal) GVIBlockManager.gvi_portal).tryToCreatePortal(worldIn, par4, par5, par6))
    			{
    				worldIn.setBlockState(pos, GVIBlockManager.foliage_fire.getDefaultState(), 11);
					par1ItemStack.damageItem(1, player);
    			}
            }
            
            Block i1 = getBlock(worldIn, par4, par5, par6);
    		if (i1 == Blocks.AIR) {
    			
    			
     		}
    		return EnumActionResult.SUCCESS;


        }
		
	}
    	
    
//helpers
	public static Block getBlock(IBlockAccess world, int i, int j, int k) {
		return world.getBlockState(new BlockPos(i, j, k)).getBlock();
	}
}