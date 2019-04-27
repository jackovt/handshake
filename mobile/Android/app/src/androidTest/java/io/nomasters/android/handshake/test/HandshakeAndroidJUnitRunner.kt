package io.nomasters.android.handshake.test

import android.os.Bundle
import cucumber.api.CucumberOptions
import cucumber.api.SnippetType
import cucumber.api.android.CucumberAndroidJUnitRunner
import java.io.File


/**
 * @author JH431939 (Jack Hughes)
 * @since 4/16/19
 */
@CucumberOptions(
    features = ["features"],
    snippets = SnippetType.CAMELCASE,
    tags = ["@LoginScreen"],
    glue = ["io.nomasters.android.handshake.test"],
    plugin = ["pretty", "html:target/cucumber"]
)
class HandshakeAndroidJUnitRunner : CucumberAndroidJUnitRunner() {
    override fun onCreate(bundle: Bundle) {
        bundle.putString(
            "plugin",
            getPluginConfigurationString()
        ) // we programmatically create the plugin configuration
        super.onCreate(bundle)
    }

    /**
     * Since we want to checkout the external storage directory programmatically, we create the plugin configuration
     * here, instead of the [CucumberOptions] annotation.
     *
     * @return the plugin string for the configuration, which contains XML, HTML and JSON paths
     */
    private fun getPluginConfigurationString(): String {
        val cucumber = "cucumber"
        val separator = "--"
        return "junit:" + getAbsoluteFilesPath() + "/" + cucumber + ".xml" + separator +
                "html:" + getAbsoluteFilesPath() + "/" + cucumber + ".html" + separator +
                "json:" + getAbsoluteFilesPath() + "/" + cucumber + ".json"
    }

    /**
     * The path which is used for the report files.
     *
     * @return the absolute path for the report files
     */
    private fun getAbsoluteFilesPath(): String {
        val directory = targetContext.getExternalFilesDir(null)
        return File(directory, "reports").getAbsolutePath()
    }
}