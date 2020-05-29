package com.ethanpilz.xyz.tabcompletors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class XYZTab implements TabCompleter {

    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){
        if (arguments.isEmpty()) {
            arguments.add("me");
            arguments.add("cross");
            arguments.add("chunk");
            arguments.add("share");
            arguments.add("sethome");
            arguments.add("home");
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);

            }
            return result;
        }

        return null;
    }

}
