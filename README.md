![Obfuscate Banner](https://i.imgur.com/Lkau0ir.png)

# Obfuscate

Obfuscate is a simple library which provides useful events, utilities, and common code for mod developers. Originally
created for compatibility in MrCrayfish's mod, this library is now targeted for all mod developers alike. **This mod
does not have any content by default.**

### Be careful

This unofficial fork updates Obfuscate to 1.18.X. If this fork has any problem, don't report this issues to the official
Obfuscate GitHub.

### Library Features:

**Manipulate the player's model safely:**

This feature allows you to easily customise the rotations of limbs on player's model without having to worry about
resetting them back to their default state. This could be used for creating a custom pose when holding a certain item,
adding custom animations when swinging a sword and so much more!

![Gif 1](https://i.imgur.com/eJQMjxY.gif)![Gif 2](https://i.imgur.com/T7LPKDc.gif)![Gif 3](https://i.imgur.com/Kh2oSin.gif)

```java
@SubscribeEvent
public void setupPlayerRotations(PlayerModelEvent.SetupAngles.Post event)
        {
        if(condition)
        {
        event.getModelPlayer().getModelHead().rotateAngleX=(float)Math.toRadians(90f);
        }
        }
```

**Full control over item rendering:**

This gives you the ability to handle rendering of items yourself. Obfuscate provides events that are called from the
true source rather than relying on the camera transform type, like vanilla's ITESR system. This means when the event for
rendering the held item on the player model is called, it's actually coming from the held rendering layer on the player
model.

```java
/* An example of controlling held item on player model. See RenderItemEvent for all events.
 * You can implement just RenderItemEvent to control all cases. */
@SubscribeEvent
public void onRenderHeldItem(RenderItemEvent.Held.Pre event)
        {
        if(condition)
        {
        event.setCancelled(true);
        //do custom rendering here
        }
        }
```

**A completely new data syncing system for players:**

It's not good practice to add data parameters to entities other than your own; this often results key mismatch due to
the uncontrolled initialization order of static fields. Obfuscate solves this issue and provides even more features
useful features. Using this new system, you can control if the parameter should be reset on death, prevent it from
syncing to all players expect the player who owns the key, prevent it from syncing at all, and even save it to the
player's data!

```java
public static final SyncedDataKey<Boolean> AIMING=SyncedDataKey.builder(Serializers.BOOLEAN)
        .id(new ResourceLocation(Reference.MOD_ID,"aiming"))
        .defaultValueSupplier(()->false)
        .resetOnDeath()
        .build();

public static void somewhereInCommonInitialization(){
        SyncedPlayerData.instance().registerKey(AIMING);
        }

//Setting value
        SyncedPlayerData.instance().set(player,ModSyncedDataKeys.AIMING,true);

//Getting value
        SyncedPlayerData.instance().get(player,ModSyncedDataKeys.AIMING)
```

### Start Developing with this Library:

You can start using this library simply by adding this code to your **build.gradle** file. You'll want to replace the
curseforge_file_id with a version of Obfuscate targeted towards your Minecraft version.

```gradle
//Minecraft 1.16.1: 3043369
//Minecraft 1.15.2: 3043368
//Minecraft 1.14.4: 2912286
//Minecraft 1.12.2: 2912288

repositories {
    maven {
        url = "https://www.cursemaven.com"
    }
}

dependencies {
    compile fg.deobf('curse.maven:obfuscate-289380:<curseforge_file_id>')
}
```
