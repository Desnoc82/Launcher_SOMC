package fr.deltadesnoc.somc.launcher.ram.poutinerp;

import fr.deltadesnoc.somc.launcher.ui.panels.PanelHomePoutineRP;
import fr.deltadesnoc.somc.launcher.utils.LauncherManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RamManager {

	public static String getRam() {
		int ram = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(PanelHomePoutineRP.PO__DIR, "ram.properties")));
			String line;
			while ((line = br.readLine()) != null) {
				ram = Integer.parseInt(line.substring(0, line.length() - 2));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = Integer.toString(ram);
		return str;
	}

	public static void setRam(String val) {
		File ramfile = new File(PanelHomePoutineRP.PO__DIR, "ram.properties");
		if (!ramfile.exists()) {
			try {
				ramfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter rama = new FileWriter(new File(PanelHomePoutineRP.PO__DIR, "ram.properties"));
			rama.write(val);
			rama.close();
		} catch (Exception localException) {}
	}
}
