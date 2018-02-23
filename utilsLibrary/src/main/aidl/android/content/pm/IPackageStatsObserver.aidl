// IPackageStatsObserver.com.julyyu.arsenal.aidl
package android.content.pm;
import android.content.pm.PackageStats;
// Declare any non-default types here with import statements
/**
 * API for package data change related callbacks from the Package Manager.
 * Some usage scenarios include deletion of cache directory, generate
 * statistics related to code, data, cache usage(TODO)
 * {@hide}
 */
oneway interface IPackageStatsObserver {
    void onGetStatsCompleted(in PackageStats pStats, boolean succeeded);
}
