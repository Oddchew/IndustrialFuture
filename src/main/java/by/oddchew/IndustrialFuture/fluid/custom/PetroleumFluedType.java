package by.oddchew.IndustrialFuture.fluid.custom;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;

import java.util.function.Consumer;

public class PetroleumFluedType extends FluidType{
    public static final PetroleumFluedType INSTANCE = new PetroleumFluedType();

    public PetroleumFluedType() {
        super(FluidType.Properties.create()
                .density(3000)
                .viscosity(6000)
                .temperature(350)
                .lightLevel(2)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY));
    }
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return new ResourceLocation(IndustrialFuture.MODID,"block/petroleum_still");
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return new ResourceLocation(IndustrialFuture.MODID, "block/petroleum_flow");
            }

        });
    }
}
