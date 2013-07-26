package net.launcher.components;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import net.launcher.run.Settings;
import net.launcher.utils.BaseUtils;
import net.launcher.utils.EncodingUtils;
import net.launcher.utils.ThreadUtils;

public class Game extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public Game(final String answer)
	{
		String user = Frame.main.offline.isSelected() ? Settings.defaultUsername : answer.split("<br>")[1].split("<:>")[0];
		String session = Frame.main.offline.isSelected() ? Settings.defaultSession : EncodingUtils.xorencode(EncodingUtils.inttostr(answer.split("<br>")[1].split("<:>")[1]), Settings.protectionKey); 
		
		try
           {
	          String cps;
	          if (System.getProperty("os.name").toLowerCase().contains("win")) {
	        	cps = ";";
	          } else {
	        	cps=":";
	          }
			  int memory = BaseUtils.getPropertyInt("memory", 512);
	          System.out.println("Running Minecraft");
	          String jarpath = BaseUtils.getMcDir().toString() + File.separator + ThreadUtils.b + File.separator;
	          String minpath = BaseUtils.getMcDir().toString() + File.separator;
	          ArrayList<String> params = new ArrayList<String>();
	          params.add("java");
	          params.add("-Xmx" + memory + "m");
	          params.add("-Dfml.ignoreInvalidMinecraftCertificates=true");
	          params.add("-Dfml.ignorePatchDiscrepancies=true");
	          params.add("-Djava.library.path="+jarpath+"natives");
	          params.add("-cp");
	          params.add(jarpath+"libraries.jar"+cps+jarpath+"extra.jar"+cps+jarpath+"Forge.jar"+cps+jarpath+"minecraft.jar");
	          params.add(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[3]);
	          params.add(user);
	          params.add(session);
	          params.add("--username");
	          params.add(user);
	          params.add("--session");
	          params.add(session);
	          params.add("--version");
	          params.add("1.6.2");
	          params.add("--gameDir");
	          params.add(minpath);
	          params.add("--assetsDir");
	          params.add(minpath+File.separator+"assets");
	          params.add("--tweakClass");
	          params.add("cpw.mods.fml.common.launcher.FMLTweaker");
	          ProcessBuilder pb = new ProcessBuilder(params);
	          pb.start();
	          System.exit(0);
	       } catch (Exception e) {
	          ;
	       }
	}
}
