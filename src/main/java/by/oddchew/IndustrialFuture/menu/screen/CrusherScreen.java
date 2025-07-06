package by.oddchew.IndustrialFuture.menu.screen;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.menu.CrusherMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static by.oddchew.IndustrialFuture.IndustrialFuture.MODID;

public class CrusherScreen extends AbstractContainerScreen<CrusherMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MODID, "textures/gui/crusher_gui.png");
    private final Inventory playerInventory;

    public CrusherScreen(CrusherMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.playerInventory = playerInventory;
        //IndustrialFuture.LOGGER.debug("CrusherScreen initialized for {}", title.getString());
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);

        // Отрисовка индикатора энергии
        int energyStored = this.menu.getBlockEntity().getEnergyStored();
        int maxEnergy = this.menu.getBlockEntity().getMaxEnergy();
        int energyScaled = maxEnergy > 0 ? energyStored * 24 / maxEnergy : 0;
        this.blit(poseStack,
                x + 138, y + 35 + (24 - energyScaled),
                176, 32 + (24 - energyScaled),
                14, energyScaled);
        // Отрисовка индикатора разрушения
        int crushProgress = this.menu.getBlockEntity().getCrushProgressScaled();
        //IndustrialFuture.LOGGER.debug("crushProgress = " + crushProgress);
        this.blit(poseStack,
                x + 58, y + 38 + (11 - crushProgress),
                176, 11 - crushProgress,
                11, crushProgress);
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        this.font.draw(poseStack, this.title, 8, 6, 0x404040);
        this.font.draw(poseStack, playerInventory.getDisplayName(), 8, this.imageHeight - 96 + 2, 0x404040);

        int energyStored = this.menu.getBlockEntity().getEnergyStored();
        int maxEnergy = this.menu.getBlockEntity().getMaxEnergy();
        String energyText = energyStored + "/" + maxEnergy + " FE";
        this.font.draw(poseStack, energyText, 90, 20, 0xFFFFFF);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }
}