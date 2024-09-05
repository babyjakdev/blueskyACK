(ns blueskyack.core
  (:import [java.io File]
           [java.util Random]))
(require '[clojure.java.io :as io]
         '[etaoin.api :as e]
         '[etaoin.keys :as k])

(defn random-img-path []
  (let [dir (File. "img")
        files (filter #(.isFile %) (.listFiles dir))
        count (count files)]
    (if (zero? count)
      nil
      (let [random-index (.nextInt (Random.) count)]
        (nth files random-index)))))

(defn bsky-login [driver user pass pps]
  (let [sign-xpath ".//button[.//div[normalize-space(text())='Sign in']]"]
    (e/wait-visible driver sign-xpath)
    (e/click driver sign-xpath))
  (let [user-xpath ".//input[@aria-label='Username or email address']"]
    (e/wait-visible driver user-xpath)
    (e/fill driver user-xpath user))
  (let [pass-xpath ".//input[@aria-label='Password']"]
    (e/wait-visible driver pass-xpath)
    (e/fill driver pass-xpath pass k/enter)))

(defn bsky-poster [driver pps]
  (let [emailver-xpath ".//div[div[text()='Not right now']]"]
    (e/wait-visible driver emailver-xpath)
    (e/click driver emailver-xpath))
  (let [newpost-xpath ".//button[contains(@aria-label, 'New post')]"]
    (e/wait-visible driver newpost-xpath)
    (e/click driver newpost-xpath))
  (let [content-xpath ".//div[@contenteditable='true']"]
    (e/wait-visible driver content-xpath)
    (e/fill driver content-xpath (slurp "message.txt")))
  (let [upload-xpath ".//button[@aria-label='Gallery']"]
    (e/wait-visible driver upload-xpath)
    (e/upload-file driver upload-xpath (random-img-path) (e/wait 30))))

(defn -main [& args]
  (let [driver (e/firefox)
        username (nth args 0)
        password (nth args 1)
        pps      (Integer. (nth args 2))]
  (e/go driver "https://bsky.app")
  (e/set-window-size driver {:width 1280 :height 800})
  (bsky-login driver username password pps)
  (bsky-poster driver pps)
  (e/quit driver)))
