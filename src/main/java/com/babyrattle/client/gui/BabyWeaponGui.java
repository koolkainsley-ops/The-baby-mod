package com.babyrattle.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import com.babyrattle.BabyRattleModInit;
import com.babyrattle.status.BabyEffects;

@Environment(EnvType.CLIENT)
public class BabyWeaponGui extends Screen {

    private static final int SLOT_SIZE = 40;
    private static final int SLOT_SPACING = 5;
    private static final int COLUMNS = 4;
    private static final int ROWS = 2;
    private static final int GUI_WIDTH = 200;
    private static final int GUI_HEIGHT = 150;

    private final ItemStack[] babyItems;
    private int selectedSlot = 0;

    public BabyWeaponGui() {
        super(Text.literal("Baby Weapons"));
        this.babyItems = new ItemStack[8];
        
        // Initialize baby items
        this.babyItems[0] = new ItemStack(BabyRattleModInit.BABY_RATTLE);
        this.babyItems[1] = new ItemStack(BabyRattleModInit.BABY_BOTTLE);
        this.babyItems[2] = new ItemStack(BabyRattleModInit.PACIFIER);
        this.babyItems[3] = new ItemStack(BabyRattleModInit.TEDDY_BEAR);
        this.babyItems[4] = new ItemStack(BabyRattleModInit.DIAPER);
        this.babyItems[5] = new ItemStack(BabyRattleModInit.MILK_BUCKET);
        this.babyItems[6] = new ItemStack(BabyRattleModInit.RATTLE_TOY);
        this.babyItems[7] = ItemStack.EMPTY;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        
        int startX = (this.width - GUI_WIDTH) / 2;
        int startY = (this.height - GUI_HEIGHT) / 2;

        // Draw background
        context.fill(startX, startY, startX + GUI_WIDTH, startY + GUI_HEIGHT, 0xFF8B4513);
        context.drawBorder(startX, startY, GUI_WIDTH, GUI_HEIGHT, 0xFFFFFFFF);

        // Draw title
        context.drawCenteredTextWithShadow(this.textRenderer, "Baby Weapon GUI", this.width / 2, startY + 10, 0xFFFFFF);

        // Draw item slots
        int slotIndex = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                int x = startX + 20 + col * (SLOT_SIZE + SLOT_SPACING);
                int y = startY + 40 + row * (SLOT_SIZE + SLOT_SPACING);

                // Draw slot background
                int slotColor = (slotIndex == selectedSlot) ? 0xFF00FF00 : 0xFF404040;
                context.fill(x, y, x + SLOT_SIZE, y + SLOT_SIZE, slotColor);
                context.drawBorder(x, y, SLOT_SIZE, SLOT_SIZE, 0xFFCCCCCC);

                // Draw item
                if (slotIndex < babyItems.length) {
                    context.drawItem(babyItems[slotIndex], x + 12, y + 12);
                }

                // Draw tooltip on hover
                if (mouseX >= x && mouseX <= x + SLOT_SIZE && mouseY >= y && mouseY <= y + SLOT_SIZE) {
                    renderItemTooltip(context, slotIndex, x, y);
                }

                slotIndex++;
            }
        }

        // Draw instructions
        context.drawTextWithShadow(this.textRenderer, "ESC to close", startX + 10, startY + GUI_HEIGHT - 20, 0xFFFFFF);
    }

    private void renderItemTooltip(DrawContext context, int slotIndex, int x, int y) {
        String[] tooltips = {
            "Baby Rattle - Shoot beams!",
            "Baby Bottle - Feed yourself",
            "Pacifier - Burp & regenerate",
            "Teddy Bear - Comfort item",
            "Diaper - Essential baby gear",
            "Milk Bucket - Power boost!",
            "Rattle Toy - Make noise!",
            ""
        };

        if (slotIndex < tooltips.length) {
            context.drawTooltip(this.textRenderer, Text.literal(tooltips[slotIndex]), x + SLOT_SIZE + 10, y);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (verticalAmount > 0) {
            selectedSlot = (selectedSlot - 1 + babyItems.length) % babyItems.length;
        } else {
            selectedSlot = (selectedSlot + 1) % babyItems.length;
        }
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) { // ESC key
            this.close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void close() {
        this.client.setScreen(null);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
