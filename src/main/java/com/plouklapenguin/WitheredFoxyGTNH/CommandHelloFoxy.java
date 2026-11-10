package com.plouklapenguin.WitheredFoxyGTNH;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandHelloFoxy extends CommandBase {

    private static final Random random = new Random();

    @Override
    public String getCommandName() {
        return "hellofoxy";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/hellofoxy";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Minecraft game = Minecraft.getMinecraft();
        int animatronic = random.nextInt(100);
        game.getSoundHandler()
            .playSound(PositionedSoundRecord.func_147673_a(FoxyHandler.JUMPSCARE));
        FoxyHandler.setFrames(animatronic);
        FoxyHandler.isPlaying = true;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true; // needed for client commands, otherwise permission checks can block it
    }
}
