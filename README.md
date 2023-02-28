# LostandFound
基于springboot的校园失物招领系统。
## 数据库设计
### Permission表

| 字段        | 类型        | 描述                               |
| ----------- | ----------- | ---------------------------------- |
| id          | bigint      | 权限ID                             |
| name        | varchar(50) | 权限名称                           |
| is_deleted  | tinyint     | 是否删除，0表示未删除，1表示已删除 |
| create_time | datetime    | 创建时间                           |
| delete_time | datetime    | 删除时间                           |

### Role表

| 字段        | 类型        | 描述                               |
| ----------- | ----------- | ---------------------------------- |
| id          | bigint      | 角色ID                             |
| name        | varchar(50) | 角色名称                           |
| is_deleted  | tinyint     | 是否删除，0表示未删除，1表示已删除 |
| create_time | datetime    | 创建时间                           |
| delete_time | datetime    | 删除时间                           |

### UserRole表

| 字段        | 类型     | 描述                               |
| ----------- | -------- | ---------------------------------- |
| id          | bigint   | 用户角色关系ID                     |
| user_id     | bigint   | 用户ID                             |
| role_id     | bigint   | 角色ID                             |
| is_deleted  | tinyint  | 是否删除，0表示未删除，1表示已删除 |
| create_time | datetime | 创建时间                           |
| delete_time | datetime | 删除时间                           |

### RolePermission表

| 字段          | 类型     | 描述                               |
| ------------- | -------- | ---------------------------------- |
| id            | bigint   | 角色权限关系ID                     |
| role_id       | bigint   | 角色ID                             |
| permission_id | bigint   | 权限ID                             |
| is_deleted    | tinyint  | 是否删除，0表示未删除，1表示已删除 |
| create_time   | datetime | 创建时间                           |
| delete_time   | datetime | 删除时间                           |

### User表

| 字段        | 类型         | 描述                               |
| ----------- | ------------ | ---------------------------------- |
| id          | bigint       | 用户ID                             |
| username    | varchar(50)  | 用户名                             |
| password    | varchar(100) | 密码                               |
| is_deleted  | tinyint      | 是否删除，0表示未删除，1表示已删除 |
| create_time | datetime     | 创建时间                           |
| delete_time | datetime     | 删除时间                           |



comment表

| 字段名      | 数据类型     | 主键/外键 | 可空 | 默认值            | 说明                               |
| ----------- | ------------ | --------- | ---- | ----------------- | ---------------------------------- |
| id          | bigint(20)   | 主键      | 否   | 自增长            | 评论ID                             |
| item_id     | bigint(20)   | 外键      | 否   | 无                | 所属物品ID，关联物品表的id字段     |
| content     | varchar(255) |           | 否   | 无                | 评论内容                           |
| user_id     | bigint(20)   | 外键      | 否   | 无                | 评论者ID，关联用户表的id字段       |
| create_time | datetime     |           | 否   | CURRENT_TIMESTAMP | 创建时间                           |
| delete_time | datetime     |           | 是   | NULL              | 删除时间，若未删除则为NULL         |
| is_deleted  | tinyint(1)   |           | 否   | 0                 | 是否删除，0表示未删除，1表示已删除 |

评论表中包含了以下字段：

- `id`: 评论ID，自增长类型。
- `item_id`: 所属物品ID，为外键，指向物品表（Item）中的对应字段。
- `content`: 评论内容。
- `user_id`: 评论者ID，为外键，指向用户表（User）中的对应字段。
- `create_time`: 记录创建时间。
- `delete_time`: 记录删除时间，若未删除则为NULL。
- `is_deleted`: 是否删除，0表示未删除，1表示已删除。

在该表中，id字段是主键，item_id和user_id字段都是外键，分别指向物品表和用户表中的对应字段。在建表时，为item_id和user_id字段都创建了外键约束，并使用CASCADE关键字表示级联删除（即当物品表或用户表中与该关联记录相关的记录被删除时，同时删除评论表中的相关记录）。

另外，该表中还加入了逻辑删除功能，即使用is_deleted字段记录是否删除，delete_time字段记录删除时间，若未删除则为NULL。这样可以保留数据记录的完整性，便于后续的数据分析和统计。
