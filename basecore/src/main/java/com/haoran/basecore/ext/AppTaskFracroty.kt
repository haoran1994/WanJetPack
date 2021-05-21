package com.haoran.basecore.ext

import com.effective.android.anchors.task.Task

/**
 * className：AppTaskFracroty
 * packageName：com.haoran.basecore.ext
 * createTime：2021/5/21 13:51
 * author： haoran
 * descrioption：AppTaskFracroty
 **/
val mTasks: MutableList<Class<*>> = arrayListOf()

//object TaskFactory{
//
//    fun initAnchors(vararg tasks:Class<*>):TaskFactory{
//        mTasks.add(InitLoadSirTask::class.java)
//        mTasks.addAll(tasks)
//        return this
//    }
//
//    fun init(task:TaskCreator){
//        if(mTasks.isEmpty()){
//          throw RuntimeException("Please initAnchors first.")
//        }
//        with(AnchorsManager.getInstance()) {
//            debuggable(BaseApplication.DEBUG)
//            for (task in mTasks){
//                addAnchor(task.simpleName)
//            }
//            start(
//                Project.Builder("app", Project.TaskFactory(task)).apply {
//                    for (task in mTasks){
//                        add(task.simpleName)
//                    }
//                }.build()
//            )
//        }
//    }
//}

class InitDefault : Task(TASK_ID, true) {
    companion object {
        const val TASK_ID = "0"
    }

    override fun run(name: String) {

    }
}

class InitLoadSirTask : Task(TASK_ID,true) {

    companion object{
        const val TASK_ID = "0"
    }

    override fun run(name: String) {
        initLoadSir()
    }

}