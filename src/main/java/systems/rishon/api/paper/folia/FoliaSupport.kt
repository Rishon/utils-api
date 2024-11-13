package systems.rishon.api.paper.folia

object FoliaSupport {

    /**
     * Check if the server is running Folia
     * @return True if the server is running Folia
     */
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