package demo;

import global.common.BaseRepository;
import global.configuration.db.mongodb.MongoDBConnection;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BlogRepositoryImpl extends BaseRepository<BlogModel> implements BlogRepository {
    private BlogModel blog;
    @Inject
    public BlogRepositoryImpl(MongoDBConnection databaseConnection) {
        super(BlogModel.class, databaseConnection);
    }
    @Override
    public BlogModel createBlog(BlogModel newBlog) {

            create(newBlog);
            return newBlog;


    }
}
