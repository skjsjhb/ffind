@file:JvmName("Main")

package moe.skjsjhb.ffind

@Suppress("UNCHECKED_CAST")
fun main() {
    runCatching {
        val mcVersion = System.getProperty("ffind.mcv");

        if (mcVersion != null) {
            runCatching {
                val fmlInjectionDataClazz = Class.forName("cpw.mods.fml.relauncher.FMLInjectionData");
                val mccversionField = fmlInjectionDataClazz.getDeclaredField("mccversion");
                mccversionField.isAccessible = true;
                mccversionField.set(null, mcVersion);
            }
        }

        val relaunchLibraryManagerClazz = Class.forName("cpw.mods.fml.relauncher.RelaunchLibraryManager")
        val rootPluginsField = relaunchLibraryManagerClazz.getDeclaredField("rootPlugins");
        rootPluginsField.isAccessible = true;
        val rootPlugins = rootPluginsField.get(null) as Array<String>;

        val libraryClasses = rootPlugins.flatMap {
            val pluginClazz = Class.forName(it);
            val pluginInstance = pluginClazz.newInstance();
            val getLibraryRequestClassMethod = pluginClazz.getMethod("getLibraryRequestClass");
            val libraryRequestClazz = getLibraryRequestClassMethod.invoke(pluginInstance);
            if (libraryRequestClazz != null) {
                (libraryRequestClazz as Array<String>).toList()
            } else {
                emptyList()
            }
        }

        libraryClasses.forEach {
            val libraryClazz = Class.forName(it);
            val libraryInstance = libraryClazz.newInstance();
            val getLibrariesMethod = libraryClazz.getMethod("getLibraries");
            val libraries = getLibrariesMethod.invoke(libraryInstance);
            if (libraries != null) {
                (libraries as Array<String>).forEach {
                    println(it);
                }
            }
        }
    }
}