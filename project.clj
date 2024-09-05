(defproject blueskyack "0.1.0"
  :description "tool to ACK! the bluesky"
  :url ""
  :license {:name "GNU General Public License v3.0"
            :url "https://www.gnu.org/licenses/gpl-3.0.html"}
  :dependencies [[org.clojure/clojure "1.11.1"] [etaoin "1.1.41"]]
  :repl-options {:init-ns blueskyack.core}
  :main blueskyack.core)
