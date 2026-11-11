
import { FiTrash2, FiEdit } from "react-icons/fi";

export default function TaskItem({
    task,
    onDelete,
    onUpdateStatus,
    onEdit
}) {
    const getStatusStyle = (status) => {
        switch (status) {
            case "COMPLETED":
                return "bg-green-100 text-green-700";
            case "IN_PROGRESS":
                return "bg-yellow-100 text-yellow-700";
            default:
                return "bg-gray-100 text-gray-700";
        }
    };

    return (
        <div className="bg-white p-6 rounded-2xl shadow-sm border border-gray-100 hover:shadow-lg transition-shadow duration-300 flex flex-col justify-between">

            <div>
                <div className="mb-4">
                    <h3 className="font-bold text-lg text-gray-800 text-left">
                        {task.title}
                    </h3>

                    <p className="text-sm text-gray-600 mt-1">
                        {task.description || "No description provided."}
                    </p>
                </div>

                <div className="mb-4 flex items-center gap-3">
                    <span className="text-sm font-medium text-gray-800">
                        Status:
                    </span>

                    <select
                        value={task.taskStatusEnum || "PENDING"}
                        onChange={(e) =>
                            onUpdateStatus(task.id, e.target.value)
                        }
                        className={`text-xs font-bold px-3 py-1.5 rounded-full cursor-pointer focus:ring-2 focus:ring-gray-300 outline-none transition ${getStatusStyle(task.taskStatusEnum)}`}
                    >
                        <option value="PENDING">PENDING</option>
                        <option value="IN_PROGRESS">IN PROGRESS</option>
                        <option value="COMPLETED">COMPLETED</option>
                    </select>
                </div>
            </div>

            <div className="flex justify-between items-center mt-4 pt-4 border-t border-gray-100 text-xs text-gray-500">

                <span>
                    Created: {task.createdAt
                        ? new Date(task.createdAt).toLocaleString()
                        : "N/A"}
                </span>

                <div className="flex items-center gap-4">
                    <button
                        onClick={() => onEdit(task)}
                        className="text-blue-500 hover:text-blue-700 transition flex items-center gap-1"
                        title="Edit task"
                    >
                        <FiEdit className="w-4 h-4" />
                        Edit
                    </button>

                    <button
                        onClick={() => onDelete(task.id)}
                        className="text-gray-400 hover:text-red-600 transition"
                        title="Delete task"
                    >
                        <FiTrash2 className="w-4 h-4" />
                    </button>
                </div>

            </div>
        </div>
    );
}
