--- Add 5 smartpoints to each schedule
INSERT INTO schedule_membership
           (schedule_id
           ,smartpoint_id
		   ,create_user
		   ,create_date)
     VALUES
            (3,1,'System',CURRENT_TIMESTAMP)
           ,(3,2,'System',CURRENT_TIMESTAMP)
           ,(3,3,'System',CURRENT_TIMESTAMP)
           ,(3,4,'System',CURRENT_TIMESTAMP)
           ,(3,5,'System',CURRENT_TIMESTAMP)
           ,(4,6,'System',CURRENT_TIMESTAMP)
           ,(4,7,'System',CURRENT_TIMESTAMP)
           ,(4,8,'System',CURRENT_TIMESTAMP)
           ,(4,9,'System',CURRENT_TIMESTAMP)
           ,(4,10,'System',CURRENT_TIMESTAMP)
           ,(5,11,'System',CURRENT_TIMESTAMP)
           ,(5,12,'System',CURRENT_TIMESTAMP)
           ,(5,13,'System',CURRENT_TIMESTAMP)
           ,(5,14,'System',CURRENT_TIMESTAMP)
           ,(5,15,'System',CURRENT_TIMESTAMP)
;
