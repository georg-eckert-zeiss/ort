/*
 * Copyright (C) 2022 The ORT Project Authors (see <https://github.com/oss-review-toolkit/ort/blob/main/NOTICE>)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

@file:Suppress("Filename", "MatchingDeclarationName")

package org.ossreviewtoolkit.model.utils

import com.fasterxml.jackson.databind.util.StdConverter

import java.util.SortedSet

import org.ossreviewtoolkit.model.CopyrightFinding
import org.ossreviewtoolkit.model.LicenseFinding
import org.ossreviewtoolkit.model.Package
import org.ossreviewtoolkit.model.PackageReference
import org.ossreviewtoolkit.model.Project
import org.ossreviewtoolkit.model.Scope

class CopyrightFindingSortedSetConverter : StdConverter<Set<CopyrightFinding>, SortedSet<CopyrightFinding>>() {
    override fun convert(value: Set<CopyrightFinding>) = value.toSortedSet(CopyrightFinding.COMPARATOR)
}

class LicenseFindingSortedSetConverter : StdConverter<Set<LicenseFinding>, SortedSet<LicenseFinding>>() {
    override fun convert(value: Set<LicenseFinding>) = value.toSortedSet(LicenseFinding.COMPARATOR)
}

class PackageReferenceSortedSetConverter : StdConverter<Set<PackageReference>, SortedSet<PackageReference>>() {
    override fun convert(value: Set<PackageReference>) = value.toSortedSet(compareBy { it.id })
}

class PackageSortedSetConverter : StdConverter<Set<Package>, SortedSet<Package>>() {
    override fun convert(value: Set<Package>) = value.toSortedSet(compareBy { it.id })
}

class ProjectSortedSetConverter : StdConverter<Set<Project>, SortedSet<Project>>() {
    override fun convert(value: Set<Project>) = value.toSortedSet(compareBy { it.id })
}

class ScopeSortedSetConverter : StdConverter<Set<Scope>, SortedSet<Scope>>() {
    override fun convert(value: Set<Scope>) = value.toSortedSet()
}

class SnippetFindingSortedSetConverter : StdConverter<Set<SnippetFinding>, SortedSet<SnippetFinding>>() {
    override fun convert(value: Set<SnippetFinding>) =
        value.toSortedSet(compareBy<SnippetFinding> { it.sourceLocation.path }.thenByDescending { it.snippet.purl })
}

// TODO: Add more converters to get rid of Comparable implementations that just serve sorted output.
