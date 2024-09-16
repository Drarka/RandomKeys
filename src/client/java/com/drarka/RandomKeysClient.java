package com.drarka;

import com.drarka.network.RandomizeKeysS2CPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.*;

public class RandomKeysClient implements ClientModInitializer {

	public static Map<String, String> keyBinds;
	public static final List<String> randomizableKeys =  Arrays.asList(new String[] {
			"key.hotbar.1", "key.hotbar.2", "key.hotbar.3", "key.hotbar.4", "key.hotbar.5", "key.hotbar.6",
			"key.hotbar.7", "key.hotbar.8", "key.hotbar.9", "key.forward", "key.back", "key.left", "key.right"
	});

	@Override
	public void onInitializeClient() {

		keyBinds = new HashMap<>();

		ClientLifecycleEvents.CLIENT_STARTED.register((client -> {
			for (KeyBinding key : client.options.allKeys) {
				keyBinds.put(key.getTranslationKey(), key.getBoundKeyTranslationKey());
			}
		}));

		ClientPlayNetworking.registerGlobalReceiver(RandomizeKeysS2CPacket.ID, (packet, context) -> {

			MinecraftClient client = context.client();
			client.execute(() -> {

				List<KeyBinding> keys = new ArrayList<>();
				List<String> originalKeys = new ArrayList<>();
				for (KeyBinding key : client.options.allKeys) {
					if(randomizableKeys.contains(key.getTranslationKey())) {
						keys.add(key);
						originalKeys.add(key.getBoundKeyTranslationKey());
					}
				}

				for (KeyBinding key : keys) {
						int randInt = new Random().nextInt(originalKeys.size());

						//if(randInt <= originalKeys.size() && randInt > 0) {

							client.options.setKeyCode(key, InputUtil.fromTranslationKey(originalKeys.get(randInt)));
							originalKeys.remove(randInt);
							KeyBinding.updateKeysByCode();
						//}
				}

			});

		});

	}

}