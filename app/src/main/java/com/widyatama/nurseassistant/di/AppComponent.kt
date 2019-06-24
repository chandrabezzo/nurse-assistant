/**
 * Copyright 2017 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dwidasa.app.proline

import com.dwidasa.app.proline.di.DatabaseModule
import com.dwidasa.app.proline.di.MyAppModule
import com.widyatama.nurseassistant.view.activity.detailTodo.DetailTodoPresenter
import com.widyatama.nurseassistant.view.activity.listPasien.ListPasienPresenter
import com.widyatama.nurseassistant.view.fragment.BottomAddPasienPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by iman on 12/28/2018.
 */

@Component(modules = arrayOf(MyAppModule::class, DatabaseModule::class))
@Singleton interface AppComponent {

    fun inject(listPasienPresenter: ListPasienPresenter)
    fun inject(bottomAddPasienPresenter: BottomAddPasienPresenter)
    fun inject(detailTodoPresenter: DetailTodoPresenter)

}
