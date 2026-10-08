package com.cihv.hellomod.sound;

import com.cihv.hellomod.HelloMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent METAL_DETECTOR_FOUND_ORE = registerSoundEvent("metal_detector_found_ore");
    public static final SoundEvent TEST_SOUND_BLOCK_BREAK = registerSoundEvent("test_sound_block_break");
    public static final SoundEvent TEST_SOUND_BLOCK_STEP = registerSoundEvent("test_sound_block_step");
    public static final SoundEvent TEST_SOUND_BLOCK_PLACE = registerSoundEvent("test_sound_block_place");
    public static final SoundEvent TEST_SOUND_BLOCK_HIT = registerSoundEvent("test_sound_block_hit");
    public static final SoundEvent TEST_SOUND_BLOCK_FALL = registerSoundEvent("test_sound_block_fall");
    public static final SoundEvent WA_WA_LIAN_MUSIC = registerSoundEvent("wa_wa_lian_music");

    public static final SoundEvent ORI_AMBIENT_SOUND = registerSoundEvent("ori_ambient_sound");
    public static final SoundEvent ORI_HURT_SOUND = registerSoundEvent("ori_hurt_sound");
    public static final SoundEvent ORI_DEATH_SOUND = registerSoundEvent("ori_death_sound");
    public static final SoundEvent ORI_DANCE_SOUND = registerSoundEvent("ori_dance_sound");

    public static final BlockSoundGroup TEST_SOUND_BLOCK_SOUNDS = new BlockSoundGroup(0.7f, 2f,
            TEST_SOUND_BLOCK_BREAK, TEST_SOUND_BLOCK_STEP, TEST_SOUND_BLOCK_PLACE, TEST_SOUND_BLOCK_HIT, TEST_SOUND_BLOCK_FALL);

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = new Identifier(HelloMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerSounds(){
        HelloMod.LOGGER.info("正在注册 "+ HelloMod.MOD_ID+" 的自定义声音");
    }
}
