package com.nghia.applen.data.repository

import com.nghia.applen.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository for social features
 */
class SocialRepository {
    
    private val _friends = MutableStateFlow<List<Friend>>(emptyList())
    val friends: Flow<List<Friend>> = _friends.asStateFlow()
    
    private val _leaderboards = MutableStateFlow<List<Leaderboard>>(emptyList())
    val leaderboards: Flow<List<Leaderboard>> = _leaderboards.asStateFlow()
    
    /**
     * Get leaderboard by type and period
     */
    suspend fun getLeaderboard(
        type: LeaderboardType,
        period: LeaderboardPeriod
    ): Result<Leaderboard> {
        return try {
            kotlinx.coroutines.delay(1000) // Simulate network
            
            val leaderboard = createMockLeaderboard(type, period)
            Result.success(leaderboard)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Get user's friends list
     */
    suspend fun getFriends(userId: String): Result<List<Friend>> {
        return try {
            kotlinx.coroutines.delay(1000)
            
            val mockFriends = createMockFriends()
            _friends.value = mockFriends
            
            Result.success(mockFriends)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Send friend request
     */
    suspend fun sendFriendRequest(
        fromUserId: String,
        toUserId: String,
        message: String?
    ): Result<FriendRequest> {
        return try {
            kotlinx.coroutines.delay(500)
            
            val request = FriendRequest(
                id = "req_${System.currentTimeMillis()}",
                fromUserId = fromUserId,
                fromUserName = "You",
                fromUserAvatar = null,
                toUserId = toUserId,
                message = message,
                createdAt = System.currentTimeMillis(),
                status = FriendStatus.PENDING
            )
            
            Result.success(request)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Get active challenges
     */
    suspend fun getChallenges(): Result<List<Challenge>> {
        return try {
            kotlinx.coroutines.delay(1000)
            
            val challenges = createMockChallenges()
            Result.success(challenges)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private fun createMockLeaderboard(
        type: LeaderboardType,
        period: LeaderboardPeriod
    ): Leaderboard {
        val entries = listOf(
            LeaderboardEntry(1, "user1", "Alice Johnson", null, 9500, 2, "🏆"),
            LeaderboardEntry(2, "user2", "Bob Smith", null, 9200, -1, "🥈"),
            LeaderboardEntry(3, "user3", "Carol White", null, 8800, 0, "🥉"),
            LeaderboardEntry(4, "user4", "David Brown", null, 8500, 3, null),
            LeaderboardEntry(5, "user5", "Eve Davis", null, 8200, -2, null),
            LeaderboardEntry(10, "current", "You", null, 7100, 5, null)
        )
        
        return Leaderboard(
            id = "lb_${type}_${period}",
            name = "${type.name} - ${period.name}",
            description = "Top performers in ${type.name.lowercase()}",
            type = type,
            period = period,
            entries = entries
        )
    }
    
    private fun createMockFriends(): List<Friend> {
        return listOf(
            Friend(
                userId = "friend1",
                userName = "Sarah Lee",
                avatarUrl = null,
                status = FriendStatus.ACCEPTED,
                addedAt = System.currentTimeMillis() - 86400000L * 30,
                lastActiveAt = System.currentTimeMillis() - 3600000L,
                stats = FriendStats(
                    currentStreak = 7,
                    totalWords = 250,
                    totalTests = 15,
                    averageScore = 85,
                    level = 5
                )
            ),
            Friend(
                userId = "friend2",
                userName = "Mike Chen",
                avatarUrl = null,
                status = FriendStatus.ACCEPTED,
                addedAt = System.currentTimeMillis() - 86400000L * 15,
                lastActiveAt = System.currentTimeMillis() - 7200000L,
                stats = FriendStats(
                    currentStreak = 3,
                    totalWords = 180,
                    totalTests = 12,
                    averageScore = 78,
                    level = 4
                )
            ),
            Friend(
                userId = "friend3",
                userName = "Emma Wilson",
                avatarUrl = null,
                status = FriendStatus.ACCEPTED,
                addedAt = System.currentTimeMillis() - 86400000L * 7,
                lastActiveAt = System.currentTimeMillis() - 86400000L,
                stats = FriendStats(
                    currentStreak = 15,
                    totalWords = 420,
                    totalTests = 28,
                    averageScore = 92,
                    level = 7
                )
            )
        )
    }
    
    private fun createMockChallenges(): List<Challenge> {
        val now = System.currentTimeMillis()
        
        return listOf(
            Challenge(
                id = "ch1",
                title = "30-Day Streak Master",
                description = "Maintain a 30-day study streak",
                type = ChallengeType.MONTHLY_STREAK,
                goal = 30,
                currentProgress = 5,
                reward = ChallengeReward(RewardType.BADGE, 100, "🔥"),
                startDate = now,
                endDate = now + 86400000L * 30,
                participants = 1247
            ),
            Challenge(
                id = "ch2",
                title = "Weekend Warrior",
                description = "Complete 5 tests this week",
                type = ChallengeType.WEEKLY_TESTS,
                goal = 5,
                currentProgress = 2,
                reward = ChallengeReward(RewardType.POINTS, 500, null),
                startDate = now,
                endDate = now + 86400000L * 7,
                participants = 892
            )
        )
    }
}
