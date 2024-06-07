package systems.rishon.api.paper.folia

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