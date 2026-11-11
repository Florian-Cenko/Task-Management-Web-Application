
import TaskItem from "./TaskItem";

export default function TaskList({
    tasks,
    onDelete,
    onUpdateStatus,
    onEdit
}) {
    if (tasks.length === 0) {
        return (
            <div className="bg-white p-10 rounded-xl text-center shadow-sm">
                <h3 className="text-lg font-semibold text-gray-700">
                    No tasks found
                </h3>

                <p className="text-gray-500 mt-2">
                    Try adding a task or changing your filters.
                </p>
            </div>
        );
    }

    return (
        <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
            {tasks.map(task => (
                <TaskItem
                    key={task.id}
                    task={task}
                    onDelete={onDelete}
                    onUpdateStatus={onUpdateStatus}
                    onEdit={onEdit}
                />
            ))}
        </div>
    );
}
