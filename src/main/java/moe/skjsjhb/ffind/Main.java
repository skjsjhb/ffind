package moe.skjsjhb.ffind;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        try {
            String mcVersion = System.getProperty("ffind.mcv");
            if (mcVersion != null) {
                try {
                    Class<?> fmlInjectionDataClazz = Class.forName("cpw.mods.fml.relauncher.FMLInjectionData");
                    Field mccversionField = fmlInjectionDataClazz.getDeclaredField("mccversion");
                    mccversionField.setAccessible(true);
                    mccversionField.set(null, mcVersion);
                } catch (Exception ignored) {
                }
            }

            Class<?> relaunchLibraryManagerClazz = Class.forName("cpw.mods.fml.relauncher.RelaunchLibraryManager");
            Field rootPluginsField = relaunchLibraryManagerClazz.getDeclaredField("rootPlugins");
            rootPluginsField.setAccessible(true);
            String[] rootPlugins = (String[]) rootPluginsField.get(null);
            for (String plugin : rootPlugins) {
                try {
                    Class<?> pluginClazz = Class.forName(plugin);
                    Object pluginInstance = pluginClazz.newInstance();
                    Method getLibraryRequestClassMethod = pluginClazz.getMethod("getLibraryRequestClass");
                    Object libraryRequestClazz = getLibraryRequestClassMethod.invoke(pluginInstance);
                    if (libraryRequestClazz != null) {
                        String[] libraryClasses = (String[]) libraryRequestClazz;
                        for (String lib : libraryClasses) {
                            Class<?> libraryClazz = Class.forName(lib);
                            Object libraryInstance = libraryClazz.newInstance();
                            Method getLibrariesMethod = libraryClazz.getMethod("getLibraries");
                            Object libraries = getLibrariesMethod.invoke(libraryInstance);
                            if (libraries != null) {
                                String[] finLibraries = (String[]) libraries;
                                for (String li : finLibraries) {
                                    System.out.println(li);
                                }
                            }
                        }
                    }
                } catch (Exception ignored) {
                }
            }
        } catch (Exception ignored) {
        }
    }
}


