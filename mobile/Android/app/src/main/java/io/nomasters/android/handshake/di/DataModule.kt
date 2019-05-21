package io.nomasters.android.handshake.di

import dagger.Module
import dagger.Provides
import io.nomasters.android.handshake.data.repo.chat.ChatSessionRepository
import io.nomasters.android.handshake.data.repo.chat.ChatSessionRepositoryImpl
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author JH431939 (Jack Hughes)
 * @since 5/5/19
 */
@Module
class DataModule {

    @Singleton
    @Named("ChatSessionRepositoryImpl")
    @Provides
    fun provideChatSessionRepository(): ChatSessionRepository {
        return ChatSessionRepositoryImpl()
    }
}