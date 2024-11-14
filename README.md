![XYZ Logo](docs/header.png)
This plugin was created in hopes that people could manage their player's locations better, instead of having to just teleport them everywhere and use long command chains to get them to different places.

![Commands Header](docs/commands.png)
## User Commands
- `/xyz me` - Displays current XYZ coordinates of your player
- `/xyz cross` (/xyz c) - Displays the XYZ coordinates of the block in your crosshair, with extra information such as light level and material type.
- `/xyz chunk` - Displays the current chunk XZ coordinates of the player.
- `/xyz share` (player) - Send your coordinates to the supplied player.
- `/xyz sethome` - Set your home
- `/xyz home` - Teleport to your home

## Admin Commands

- `/xyza version` - Display the current version of the plugin, what spigot build it is for, and when it was built.
- `/xyza other (player)` - Display the coordinates of another player's location.
- `/xyza relocate (player)` - Teleport the specified player within a 5000 block radius randomly.Fun to do on yourself.
- `/xyza tp (player) (player)` - Teleports 1st player to 2nd player. Can be used with only one player, which will teleport you to the specified player.
- `/xyza swap (player) (player)` - Swap the player locations of both specified players. Plays a noise to both players.
- `/xyza freeze (player)` - A toggle command which will render the specified player completely unable to move. Use again to unfreeze.
- `/xyza distance (player)` - Show the distance in number of blocks between you and another player.
- `/xyza serverinfo` - Show basic server info such as IP address and Spigot version.
- `/xyza spawn (player)` - Teleport the specified player to the world spawn that they're currently in.
- `/xyza stalk (player)` - Teleport to the specified player and turn invisible to observe them.
- `/xyza clear (player)` - Clear effects on specified player. Don't specify a player for it to clear your effects.
- `/xyza blind (player)` - A toggle command which will blind the specified player. Use again to remove blindness.
- `/xyza portals (lock/unlock)` - Enable/Disable portal travel for all members of the server.
- `/xyza lockdown` - Toggle command to disable joining of the server and movement for those who do not have xyz.admin
- `/xyza players` - Display an inventory GUI of players and information about them. Click the head to teleport.
- `/xyza world (world)` - See specific information on the supplied world.
- `/xyza home (player)` - teleport to the supplied player's home.

## Legacy Links
- [Spigot Page](https://www.spigotmc.org/resources/xyz.30713/)

## License
Copyright (C) 2020 Ethan Pilz

You are permitted to use this plugin for use on your server, regardless of size. You are not permitted to redistribute and/or post the plugin to another site for download, whether you claim it is your own or not. You also not permitted to sell the plugin, it is always to be free.
