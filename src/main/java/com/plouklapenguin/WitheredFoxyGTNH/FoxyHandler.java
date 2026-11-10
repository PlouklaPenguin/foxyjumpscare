package com.plouklapenguin.WitheredFoxyGTNH;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class FoxyHandler {

    public static final ResourceLocation JUMPSCARE = new ResourceLocation("witheredfoxygtnh", "jumpscare");
    private static final ResourceLocation[] WFOXY_FRAMES = {
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy0.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy1.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy2.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy3.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy4.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy5.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy6.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy7.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy8.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy9.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy10.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy11.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy12.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/wfoxy13.png")};
    private static final ResourceLocation[] MANGLE_FRAMES = {
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle0.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle1.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle2.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle3.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle4.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle5.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle6.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle7.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle8.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle9.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle10.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle11.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle12.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle13.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle14.png"),
        new ResourceLocation("witheredfoxygtnh", "textures/gui/mangle15.png")};
    private static final Random random = new Random();
    public static boolean isPlaying;
    public static ResourceLocation[] FRAMES;
    private static Minecraft game = null;
    private static int tickCounter;

    private int currentFrame = 0;
    private long lastFrameTime = 0;

    public static void setFrames(int animatronic) {
        if (animatronic < 25) {
            FRAMES = MANGLE_FRAMES;
        } else {
            FRAMES = WFOXY_FRAMES;
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        game = Minecraft.getMinecraft();

        tickCounter++;

        if (tickCounter >= 20) {
            tickCounter = 0;
            chanceCheck();
        }
    }

    private void chanceCheck() {
        if (random.nextInt(10000) == 0) {
            int animatronic = random.nextInt(100);

            game.getSoundHandler()
                .playSound(PositionedSoundRecord.func_147673_a(JUMPSCARE));
            setFrames(animatronic);
            isPlaying = true;
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !isPlaying) return;

        long time = System.currentTimeMillis();
        if (time - lastFrameTime > 33) {
            if (currentFrame < FRAMES.length - 1) {
                currentFrame += 1;
                lastFrameTime = time;
            } else {
                currentFrame = 0;
                isPlaying = false;
            }
        }

        Minecraft game = Minecraft.getMinecraft();
        game.entityRenderer.setupOverlayRendering();
        ScaledResolution res = new ScaledResolution(game, game.displayWidth, game.displayHeight);
        int x = 0, y = 0;
        int screenWidth = res.getScaledWidth();
        int screenHeight = res.getScaledHeight();

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);

        game.getTextureManager()
            .bindTexture(FRAMES[currentFrame]);
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertexWithUV(x, y + screenHeight, 0, 0, 1);
        t.addVertexWithUV(x + screenWidth, y + screenHeight, 0, 1, 1);
        t.addVertexWithUV(x + screenWidth, y, 0, 1, 0);
        t.addVertexWithUV(x, y, 0, 0, 0);
        t.draw();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

    }
}
