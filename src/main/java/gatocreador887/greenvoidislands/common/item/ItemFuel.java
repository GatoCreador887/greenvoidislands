package gatocreador887.greenvoidislands.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFuel extends Item {
	
	protected int burnTime;
	
	public ItemFuel(int burnTimeIn) {
		super();
		
		this.burnTime = burnTimeIn;
		
	}
	
	@Override
	public int getItemBurnTime(ItemStack stack) {
		
		return this.burnTime;
		
	}
	
}
