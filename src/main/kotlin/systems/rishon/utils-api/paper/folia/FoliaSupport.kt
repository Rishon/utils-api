package systems.rishon.selautils.paper.folia

object FoliaSupport {

    @JvmStatic
    fun isFolia(): Boolean {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer")
            return true
        } catch (exception: ClassNotFoundException) {
            return false
        }
    }

}