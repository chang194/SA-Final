package com.example.database.data.repository

import com.example.database.data.datasource.MyDao
import com.example.database.data.entity.MemberEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
Clean architecture pattern
good for separation of logic
 */

/* creating an interface helps in abstraction and easily we can add or remove
required methods without a hassle.
*/
interface MemberRepository {

    suspend fun insert(memberEntity: MemberEntity)

    suspend fun delete(memberEntity: MemberEntity)

    suspend fun update(memberEntity: MemberEntity)

    suspend fun getAllMember(): Flow<List<MemberEntity>>
}

class RepositoryImpl @Inject constructor(
    private val dao: MyDao,
) : MemberRepository {
    // 檢查帳號是否存在
    suspend fun isEmailExists(account: String): Boolean {
        return withContext(IO) {
            dao.getMemberByEmail(account) != null
        }
    }

    override suspend fun insert(memberEntity: MemberEntity) {
        // 檢查帳號是否已存在
        val emailExists = isEmailExists(memberEntity.email)
        if (!emailExists) {
            // 帳號不存在，執行插入操作
            withContext(IO) {
                dao.insert(memberEntity)
            }
        } else {
            // 帳號已存在
        }
    }

    override suspend fun delete(memberEntity: MemberEntity) {
        withContext(IO) {
            dao.delete(memberEntity)
        }
    }

    override suspend fun update(memberEntity: MemberEntity) {
        withContext(IO) {
            dao.update(memberEntity)
        }
    }

    override suspend fun getAllMember(): Flow<List<MemberEntity>> {
        return withContext(IO) {
            dao.getAllMember()
        }
    }

}