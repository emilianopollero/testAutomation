package SeleniumAutomation.Utils;

import java.util.Locale;

    final class OsCheck {
        /**
         * types of Operating Systems
         */
        public enum OSType {
            Windows, MacOS, Linux, Other
        }

        private static OSType detectedOS;

        static OSType getOperatingSystemType() {
            if (detectedOS == null) {
                String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
                if ((OS.contains("mac")) || (OS.contains("darwin"))) {
                    detectedOS = OSType.MacOS;
                } else if (OS.contains("win")) {
                    detectedOS = OSType.Windows;
                } else if (OS.contains("nux")) {
                    detectedOS = OSType.Linux;
                } else {
                    detectedOS = OSType.Other;
                }
            }
            return detectedOS;
        }
}
