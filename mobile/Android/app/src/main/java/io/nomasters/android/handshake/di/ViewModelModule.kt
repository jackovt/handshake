package io.nomasters.android.handshake.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.nomasters.android.handshake.ui.chatlist.ChatListViewModel
import io.nomasters.android.handshake.view.viewmodel.DaggerViewModelFactory

/**
 * @author JH431939 (Jack Hughes)
 * @since 5/5/19
 */
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ChatListViewModel::class)
    abstract fun bindChatListViewModel(myViewModel: ChatListViewModel): ViewModel
}