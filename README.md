# LostandFound
基于前后端分离的校园失物招领系统设计与实现。
## 数据库设计

## 角色表

```sql
CREATE TABLE `roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` VARCHAR(50) NOT NULL COMMENT '角色名称',
  `description` VARCHAR(255) COMMENT '角色描述',
  `created_time` DATE NOT NULL COMMENT '创建时间',
  `update_time` DATE COMMENT '删除时间',
  `is_deleted` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除：0-未删除，1-已删除',
  `created_by` INT(11) NOT NULL COMMENT '创建者ID',
  `updated_by` INT(11) NOT NULL COMMENT '最后修改者ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '角色表';
```

上述表中的 `id`、`name`、`created_time`、`deleted_time`、`is_deleted`、`created_by`和`updated_by` 分别代表角色ID、角色名称、创建时间、删除时间、是否逻辑删除、创建者ID和最后修改者ID，其中：

- `id`：自增主键，用于唯一标识每一个角色；
- `name`：角色名称，不允许为空；
- `description`：角色描述，可为空，用于描述角色的一些具体信息；
- `created_time`：角色创建时间，默认为当前时间；
- `deleted_time`：角色删除时间，默认为空，如果角色被逻辑删除，则此字段记录删除时间；
- `is_deleted`：是否逻辑删除标志位，默认为0，表示未删除；如果为1，则表示已被逻辑删除；
- `created_by`：创建者ID，用于记录创建者的ID信息；
- `updated_by`：最后修改者ID，用于记录最后修改者的ID信息。

这个角色表的设计可以根据具体需求进行修改和扩展，例如可以添加更多的列来存储其他角色信息，或者根据需要增加其他的索引来提高查询性能。



## 权限表

```sql
CREATE TABLE permissions (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '权限ID',
    name VARCHAR(50) NOT NULL COMMENT '权限名称',
    description TEXT COMMENT '权限描述',
    created_time DATE NOT NULL COMMENT '创建时间',
    updated_time DATE NOT NULL COMMENT '更新时间',
    is_deleted BOOLEAN NOT NULL DEFAULT false COMMENT '是否逻辑删除'
);
```

在这个表中，我们使用了 `id` 作为主键，这个字段会自动递增。同时，我们添加了 `name` 和 `description` 字段，用于描述该权限的名称和详情。这些字段都使用了常规的 VARCHAR 和 TEXT 数据类型。

此外，我们也为表中的 `created_time` 和 `updated_time` 字段添加了 `DATE` 数据类型，用于存储对应权限的创建和更新时间。我们也添加了 `is_deleted` 字段，用于标识该权限是否已被逻辑删除，这个字段使用了 `BOOLEAN` 数据类型，并设置了默认值为 `false`。

最后，我们也为每个字段添加了注释，方便其他开发者阅读和理解这个表的设计意图。



## 帖子表

```sql
CREATE TABLE post (
  id INT AUTO_INCREMENT COMMENT '帖子ID',
  title VARCHAR(255) NOT NULL COMMENT '标题',
  content TEXT NOT NULL COMMENT '内容',
  author_id INT NOT NULL COMMENT '作者ID',
  created_time DATE NOT NULL COMMENT '创建时间',
  updated_time DATE NOT NULL COMMENT '更新时间',
  is_deleted BOOLEAN NOT NULL DEFAULT false COMMENT '逻辑删除标识',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态（1：正常，2：待审核，3：已删除，4：已禁用）',
  PRIMARY KEY (id),
  FOREIGN KEY (author_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT '帖子表';
```

### 说明：

1. `id` 字段为自增长的帖子 ID。
2. `title` 和 `content` 字段分别为帖子的标题和内容，其中 `content` 使用 `TEXT` 类型以支持较大的文本内容。
3. `author_id` 字段为帖子作者的 ID，使用整数类型，并与 `user` 表中的 `id` 字段关联。
4. `created_time` 和 `updated_time` 字段分别为帖子的创建时间和更新时间，使用 `DATE` 类型存储日期信息。
5. `is_deleted` 字段为逻辑删除标识，使用布尔类型存储，初始值为 `false`。
6. 主键为 `id` 字段，外键为 `author_id` 字段，使用 `CASCADE` 选项表示当关联的用户被删除或更新时，对应的帖子也会被删除或更新。
7. 对表和各个字段都添加了相应的注释，以方便理解和维护。

### 更新

1. 新增了 `status` 字段，使用 `TINYINT` 类型存储帖子的状态信息，其值对应不同的状态，例如 `1` 表示正常状态，`2` 表示待审核状态，`3` 表示已删除状态，`4` 表示已禁用状态。
2. `status` 字段使用 `DEFAULT` 关键字指定初始值为 `1`，表示帖子默认为正常状态。
3. 对 `status` 字段也添加了注释以便理解。



## 评论表

```sql
CREATE TABLE comments (
  id INT AUTO_INCREMENT PRIMARY KEY,
  post_id INT NOT NULL COMMENT '关联帖子表id',
  commenter_id INT NOT NULL COMMENT '评论人id',
  commented_user_id INT NOT NULL COMMENT '被评论人id',
  content TEXT NOT NULL COMMENT '评论内容',
  created_time DATE NOT NULL COMMENT '创建时间',
  updated_time DATE COMMENT '更新时间',
  deleted_time DATE COMMENT '删除时间，若为null则未删除',
  comment_type INT NOT NULL COMMENT '评论类型，分为一级评论和二级评论',
  parent_id INT DEFAULT NULL COMMENT '父级评论id，若为null则为一级评论',
);
```

### 说明：

- `id`：评论的唯一标识符，使用 `INT` 类型，并设置为自增。
- `parent_id`：表示当前评论是哪个父级评论的回复，如果为一级评论则为 null，使用 `INT` 类型。
- `post_id`：表示当前评论是哪个帖子下的评论，使用 `INT` 类型，不允许为 null。
- `commenter_id`：表示发表该评论的用户 ID，使用 `INT` 类型，不允许为 null。
- `commented_user_id`：表示被评论的用户 ID，使用 `INT` 类型，不允许为 null。
- `content`：评论的内容，使用 `TEXT` 类型，不允许为 null。
- `comment_type`:评论类型，使用`INT`类型，分为一级评论和二级评论
- `created_time`：评论的创建时间，使用 `DATE` 类型，不允许为 null，并添加了 `COMMENT` 注释。
- `updated_time`：评论的更新时间，使用 `DATE` 类型，允许为 null，并添加了 `COMMENT` 注释。
- `deleted_time`：评论的删除时间，使用 `DATE` 类型，允许为 null，并添加了 `COMMENT` 注释。



## 点赞表

```sql
CREATE TABLE likes (
  id INT AUTO_INCREMENT PRIMARY KEY,        -- 点赞ID
  user_id INT NOT NULL,                     -- 用户ID
  post_id INT NOT NULL,                     -- 帖子ID
  created_time DATE NOT NULL COMMENT '创建时间',  -- 创建时间
  updated_time DATE NOT NULL COMMENT '更新时间'  -- 更新时间
);
```

### 说明：

- `id`：点赞的唯一标识符，采用自增长整数的方式生成；
- `user_id`：点赞的用户ID，非空；
- `post_id`：被点赞的帖子ID，非空；
- `created_time`：点赞创建的时间，使用DATE类型存储，非空，加上了comment注释；
- `updated_time`：点赞更新的时间，使用DATE类型存储，非空，加上了comment注释；
- `fk_likes_users`：该外键确保了每个点赞都关联到一个现有的用户；
- `fk_likes_posts`：该外键确保了每个点赞都关联到一个现有的帖子。

这个表能够记录每个用户对每个帖子的点赞情况，同时也可以追踪点赞的创建和更新时间。如果需要在此基础上进行进一步的扩展，可以添加其他字段，如点赞状态（例如，是否已经取消点赞）、点赞评论等。



## 收藏表

```sql
CREATE TABLE collection (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL COMMENT '用户ID',
  post_id INT NOT NULL COMMENT '帖子ID',
  created_time DATE NOT NULL COMMENT '创建时间',
  updated_time DATE NOT NULL COMMENT '更新时间',
  description TEXT COMMENT '收藏描述'
);
```

### 说明：

- `id`：自增主键，唯一标识一条收藏记录。
- `user_id`：整数类型，表示收藏该帖子的用户ID。
- `post_id`：整数类型，表示被收藏的帖子ID。
- `created_time`：日期类型，表示该收藏记录的创建时间。
- `updated_time`：日期类型，表示该收藏记录的更新时间。
- `description`：文本类型，表示用户对该收藏的描述或备注。

注释说明：

- `user_id`、`post_id`、`created_time` 和 `updated_time` 字段添加了注释，以提高表结构的可读性。
- `created_time` 和 `updated_time` 字段类型为 `DATE`，以存储日期信息。
- 由于表关联在后端处理，所以没有设置外键。



## 用户角色表

```sql
CREATE TABLE user_role (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL COMMENT '用户ID',
  role_id INT NOT NULL COMMENT '角色ID',
  created_time DATE NOT NULL COMMENT '创建时间',
  updated_time DATE NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';
```

### 说明：

- `id`: 自增的唯一标识符，用作主键。
- `user_id`: 用户ID，表示该角色是哪个用户拥有的。
- `role_id`: 角色ID，表示该角色的ID。
- `created_time`: 创建时间，表示该用户角色的创建时间。
- `updated_time`: 更新时间，表示该用户角色的最后更新时间。



## 角色权限表

```sql
CREATE TABLE role_permission (
  id INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  role_id INT NOT NULL COMMENT '角色ID',
  permission_id INT NOT NULL COMMENT '权限ID',
  created_time DATE NOT NULL COMMENT '创建时间',
  updated_time DATE NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';
```

### 说明：

- `id`: 自增的唯一标识符，用作主键。
- `role_id`: 角色ID，表示该权限属于哪个角色。
- `permission_id`: 权限ID，表示该角色所拥有的权限的ID。
- `created_time`: 创建时间，表示该角色权限的创建时间。
- `updated_time`: 更新时间，表示该角色权限的最后更新时间。
