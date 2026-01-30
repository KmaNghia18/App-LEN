package com.nghia.applen.android.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nghia.applen.android.MainActivity
import com.nghia.applen.android.R

class NotificationHelper(private val context: Context) {
    
    companion object {
        private const val CHANNEL_ID_REMINDERS = "study_reminders"
        private const val CHANNEL_ID_ACHIEVEMENTS = "achievements"
        private const val CHANNEL_ID_DAILY_WORD = "daily_word"
        
        private const val NOTIFICATION_ID_REMINDER = 1001
        private const val NOTIFICATION_ID_ACHIEVEMENT = 1002
        private const val NOTIFICATION_ID_DAILY_WORD = 1003
    }
    
    init {
        createNotificationChannels()
    }
    
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val reminderChannel = NotificationChannel(
                CHANNEL_ID_REMINDERS,
                "Study Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Reminders to study vocabulary and maintain your streak"
            }
            
            val achievementChannel = NotificationChannel(
                CHANNEL_ID_ACHIEVEMENTS,
                "Achievements",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for unlocked achievements and milestones"
            }
            
            val dailyWordChannel = NotificationChannel(
                CHANNEL_ID_DAILY_WORD,
                "Word of the Day",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Daily word suggestions"
            }
            
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(reminderChannel)
            notificationManager.createNotificationChannel(achievementChannel)
            notificationManager.createNotificationChannel(dailyWordChannel)
        }
    }
    
    /**
     * Show study reminder notification
     */
    fun showStudyReminder(dueCardsCount: Int) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID_REMINDERS)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Time to Study! 📚")
            .setContentText("You have $dueCardsCount words to review. Keep your streak alive!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        
        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID_REMINDER, notification)
        }
    }
    
    /**
     * Show achievement unlocked notification
     */
    fun showAchievement(title: String, message: String) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID_ACHIEVEMENTS)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("🏆 $title")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        
        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID_ACHIEVEMENT, notification)
        }
    }
    
    /**
     * Show daily word notification
     */
    fun showDailyWord(word: String, definition: String) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID_DAILY_WORD)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("📖 Word of the Day: $word")
            .setContentText(definition)
            .setStyle(NotificationCompat.BigTextStyle().bigText(definition))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        
        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID_DAILY_WORD, notification)
        }
    }
    
    /**
     * Show streak reminder notification
     */
    fun showStreakReminder(currentStreak: Int) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID_REMINDERS)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("🔥 Don't Break Your Streak!")
            .setContentText("You're on a $currentStreak day streak. Study today to keep it going!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        
        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID_REMINDER, notification)
        }
    }
}
