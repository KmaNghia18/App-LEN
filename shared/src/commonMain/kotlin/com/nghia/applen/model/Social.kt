package com.nghia.applen.model

import kotlinx.serialization.Serializable

/**
 * Social features models
 */

@Serializable
data class Leaderboard(
    val id: String,
    val name: String,
    val description: String,
    val type: LeaderboardType,
    val period: LeaderboardPeriod,
    val entries: List<LeaderboardEntry>
)

@Serializable
enum class LeaderboardType {
    TOTAL_SCORE,
    STREAK,
    WORDS_LEARNED,
    TESTS_COMPLETED,
    STUDY_TIME
}

@Serializable
enum class LeaderboardPeriod {
    DAILY,
    WEEKLY,
    MONTHLY,
    ALL_TIME
}

@Serializable
data class LeaderboardEntry(
    val rank: Int,
    val userId: String,
    val userName: String,
    val avatarUrl: String?,
    val score: Int,
    val change: Int? = null, // Rank change from previous period
    val badge: String? = null // Special badge for top performers
)

@Serializable
data class Friend(
    val userId: String,
    val userName: String,
    val avatarUrl: String?,
    val status: FriendStatus,
    val addedAt: Long,
    val lastActiveAt: Long,
    val stats: FriendStats
)

@Serializable
enum class FriendStatus {
    PENDING,
    ACCEPTED,
    BLOCKED
}

@Serializable
data class FriendStats(
    val currentStreak: Int,
    val totalWords: Int,
    val totalTests: Int,
    val averageScore: Int,
    val level: Int
)

@Serializable
data class FriendRequest(
    val id: String,
    val fromUserId: String,
    val fromUserName: String,
    val fromUserAvatar: String?,
    val toUserId: String,
    val message: String?,
    val createdAt: Long,
    val status: FriendStatus
)

@Serializable
data class StudyGroup(
    val id: String,
    val name: String,
    val description: String,
    val avatarUrl: String?,
    val members: List<GroupMember>,
    val createdBy: String,
    val createdAt: Long,
    val isPublic: Boolean,
    val memberCount: Int,
    val maxMembers: Int = 50
)

@Serializable
data class GroupMember(
    val userId: String,
    val userName: String,
    val avatarUrl: String?,
    val role: GroupRole,
    val joinedAt: Long,
    val contribution: Int // Study points contributed
)

@Serializable
enum class GroupRole {
    OWNER,
    ADMIN,
    MEMBER
}

@Serializable
data class Challenge(
    val id: String,
    val title: String,
    val description: String,
    val type: ChallengeType,
    val goal: Int,
    val currentProgress: Int,
    val reward: ChallengeReward,
    val startDate: Long,
    val endDate: Long,
    val participants: Int
)

@Serializable
enum class ChallengeType {
    DAILY_WORDS,
    WEEKLY_TESTS,
    MONTHLY_STREAK,
    SCORE_TARGET,
    GROUP_CHALLENGE
}

@Serializable
data class ChallengeReward(
    val type: RewardType,
    val value: Int,
    val badge: String?
)

@Serializable
enum class RewardType {
    POINTS,
    BADGE,
    ACHIEVEMENT,
    PREMIUM_DAYS
}
