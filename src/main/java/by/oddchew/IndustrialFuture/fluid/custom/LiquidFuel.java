package by.oddchew.IndustrialFuture.fluid.custom;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class LiquidFuel extends BucketItem {
    public LiquidFuel(RegistryObject< FlowingFluid > Liquid_Source, Properties props) {
        super(Liquid_Source, props);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {return 12800;}
}
