(ns blueskyack.core)
(require '[clojure.java.io :as io]
         '[etaoin.api :as e]
         '[etaoin.keys :as k])

(defn random-img-path []
  (let [directory (io/file "img")
        files (filter #(.isFile %) (file-seq directory))
        random-file (rand-nth files)]
    (io/file "img" (.getName random-file))))

(defn bsky-login [driver user pass]
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
  (loop []
    (let [composer-xpath ".//button[contains(@aria-label, 'New post')]"]
      (e/wait-visible driver composer-xpath)
      (e/click driver composer-xpath))
    (let [content-xpath ".//div[@contenteditable='true']"]
      (e/wait-visible driver content-xpath)
      (e/fill driver content-xpath (slurp "message.txt")))
    (let [upload-xpath ".//button[@aria-label='Gallery']"]
      (e/wait-visible driver upload-xpath)
      (e/click driver upload-xpath)
      (let [input-xpath "//input[@type='file' and @accept='image/*']"]
        (e/wait-exists driver input-xpath)
        (e/js-execute driver "document.querySelector('input[type=\"file\"]').style.display = 'block';")
        (e/upload-file driver input-xpath (random-img-path))))
    (let [publish-xpath ".//button[@aria-label='Publish post']"]
      (e/wait-visible driver publish-xpath)
      (e/click driver publish-xpath))
    (e/wait pps)
    (recur)))

(defn -main [& args]
  (let [driver (e/chrome-headless)
        username (nth args 0)
        password (nth args 1)
        pps      (Integer. (nth args 2))]
  (e/go driver "https://bsky.app")
  (e/set-window-size driver {:width 1280 :height 800})
  (bsky-login driver username password)
  (bsky-poster driver pps)
  (e/quit driver)))
