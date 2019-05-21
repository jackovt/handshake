package io.nomasters.android.handshake.di

import dagger.Component
import io.nomasters.android.handshake.MainActivity
import io.nomasters.android.handshake.MainApplication
import io.nomasters.android.handshake.ui.chat.ChatFragment
import io.nomasters.android.handshake.ui.chatlist.ChatListFragment
import javax.inject.Singleton

/**
 * @author JH431939 (Jack Hughes)
 * @since 5/4/19
 */
@Singleton
@Component(
    modules = [DataModule::class, ViewModelModule::class]
)
interface MainComponent {
    fun inject(mainApplication: MainApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(chatListFragment: ChatListFragment)
    fun inject(chatFragment: ChatFragment)
}