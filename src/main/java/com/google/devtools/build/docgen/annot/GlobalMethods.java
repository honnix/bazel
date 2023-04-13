// Copyright 2020 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.docgen.annot;

import com.google.common.base.Ascii;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation applied to a class that indicates to docgen that the class's {@link
 * net.starlark.java.annot.StarlarkMethod}-annotated methods should be included in docgen's output
 * as standalone global functions.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalMethods {
  /** The environment in which the global methods in the annotated class are available. */
  enum Environment {
    BUILD(
        "BUILD files",
        "This page contains global methods available in BUILD files. See also the Build"
            + " Encyclopedia for extra <a href=\"${link functions}\">functions</a> and build rules,"
            + " which can also be used in BUILD files."),
    WORKSPACE("WORKSPACE files", "This page contains global methods available in WORKSPACE files."),
    MODULE(
        "MODULE.bazel files", "This page contains global methods available in MODULE.bazel files."),
    BZL(".bzl files", "This page contains global methods available in all .bzl files."),
    ALL(
        "All Starlark files",
        "This page contains global methods available in all Starlark files, including BUILD,"
            + " WORKSPACE, MODULE.bazel, and all .bzl files.");

    private final String title;
    private final String description;

    Environment(String title, String description) {
      this.title = title;
      this.description = description;
    }

    public String getTitle() {
      return title;
    }

    public String getDescription() {
      return description;
    }

    public String getPath() {
      return Ascii.toLowerCase(name());
    }
  }

  Environment[] environment();
}
