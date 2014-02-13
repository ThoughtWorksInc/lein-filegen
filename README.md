# lein-filegen

A Leiningen plugin that generates files based on the instructions in project.clj.

This is useful for generating artefacts outside of src such as configuration files etc.

## Usage

Use this for project-level plugins:

Put `[lein-filegen "0.1.0-SNAPSHOT"]` into the `:plugins` vector of your project.clj.

## Configuration

To use lein-filegen you'll need to add a :filegen vector into your project.clj.

The filegen vector contains a hash which prescribes how to generate a file.  It needs to know:

* `:target` file to generate
* `:data` from which to generate the file
* `:template-fn` to transform the data into the string which will be written to the file
* `:requires`, an optional argument to require any namespaces for the template-fn to work

In full it looks something like this:

```clojure
:filegen [{:data {:environment ~(System/getenv "ENVIRONMENT")
                  :database-url ~(System/getenv "DATABASE_URL")}
             :requires '[clojure.data.json :refer [write-str]]
             :template-fn write-str
             :target "resources/configuration/config.json"}]
```

The above takes the data and converts it to a json file.

As it's a vector filegen can take multiple hash instructions for different files.

## Run
    $ lein filegen

## License

Copyright © 2014 Thoughtworks Inc

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
